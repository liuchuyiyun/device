/**
 * Created by lx on 2016/11/9.
 */
var upload = function(input){
    if(window.FormData)
    {
        if(input.val() == '' || input.val() == 'undefined')
            return false;

        var files = input[0].files;//保存要上传的文件
        var formData = new FormData();//文件上传通道
        var fileReader = new FileReader();

        var getFileType = function(filename){
            var extStart  = filename.lastIndexOf(".")+1;
            return filename.substring(extStart,filename.length).toUpperCase();
        };

        var clearInput = function(){
            input.val('');
        };

        var getKey = function(){//获得一个key  10位数字  这样可以保证几率比较小了  假如还想要几率小  可以计算md5
            return Math.floor(Math.random()*10000000000);
        };

        var key = getKey();//保存文件的唯一key
        var url = input.attr('data-url')||arguments[1].url;//上传地址
        var progress = arguments[1].progress;//上传进度条
        var complete = arguments[1].complete;//上传完成
        var error = arguments[1].error;//上传失败
        var maxSize = arguments[1].maxSize;//文件大小  单位字节  过滤器
        var fileType = arguments[1].fileType;//文件类型  过滤器
        var data = arguments[1].data;//额外数据
        var onStart = arguments[1].onStart;//准备上传
        var abortBtn = arguments[1].abortBtn;//中断上传按钮
        var onAbort = arguments[1].onAbort;//中断函数
        //文件类型验证
        var type = getFileType(files[0].name);//获得文件类型
        var typeAllow = false;
        if(typeof fileType == 'object')
        {
            var arrayType = fileType.type;
            $.each(arrayType,function(index,value){
                if(value.toUpperCase() == type)
                {
                    typeAllow = true;
                    return false;//相当于break
                }
            });
            if(!typeAllow)
            {
                fileType.error(files[0],key);
                return false;
            }
        }
        //文件大小验证
        if(typeof maxSize == 'number')
        {
            if(files[0].size >= maxSize)
            {
                maxSize.error(files[0],key);
                return false;
            }
        }
        else if(typeof maxSize == 'object')
        {
            if(files[0].size >= maxSize.size)
            {
                maxSize.error(files[0],key);
                return false;
            }
        }
        //将文件加入到上传通道
        formData.append(input.attr('name')||'files', files[0]);
        var xhr = new XMLHttpRequest();
        //附加额外的数据
        if(data != '' && data != 'undefined')
        {
            $.each(data,function(index,value){
                formData.append(index,value);
            });
        }
        //开始上传
        xhr.open('POST',url,true);
        xhr.onload = function(){
            if(xhr.status == 200 && xhr.readyState == 4)
            {
                if(typeof complete == 'function')
                    complete(xhr.response,files[0],key);
                //clearInput();
            }
            else
            {
                if(typeof error == 'function')
                    error(files[0],key);
            }
        };
        xhr.upload.onprogress = function(event){
            progress(event,files[0],key);
        };
        xhr.onloadstart = function(event){
            onStart(event,files[0],key);
        };
        xhr.send(formData);
        //添加中断
        $('.'+key).find(abortBtn).click(function(){
            xhr.abort();
            onAbort(files[0],key);
        });
        return {key:key,xhr:xhr};
    }
    else
    {
        alert('浏览器版本过低');
        return null;
    }
}