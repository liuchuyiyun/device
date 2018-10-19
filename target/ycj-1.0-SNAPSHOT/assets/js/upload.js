/**
 * Created by lx on 2016/11/9.
 */
var upload = function(input){
    if(window.FormData)
    {
        if(input.val() == '' || input.val() == 'undefined')
            return false;

        var files = input[0].files;//����Ҫ�ϴ����ļ�
        var formData = new FormData();//�ļ��ϴ�ͨ��
        var fileReader = new FileReader();

        var getFileType = function(filename){
            var extStart  = filename.lastIndexOf(".")+1;
            return filename.substring(extStart,filename.length).toUpperCase();
        };

        var clearInput = function(){
            input.val('');
        };

        var getKey = function(){//���һ��key  10λ����  �������Ա�֤���ʱȽ�С��  ���绹��Ҫ����С  ���Լ���md5
            return Math.floor(Math.random()*10000000000);
        };

        var key = getKey();//�����ļ���Ψһkey
        var url = input.attr('data-url')||arguments[1].url;//�ϴ���ַ
        var progress = arguments[1].progress;//�ϴ�������
        var complete = arguments[1].complete;//�ϴ����
        var error = arguments[1].error;//�ϴ�ʧ��
        var maxSize = arguments[1].maxSize;//�ļ���С  ��λ�ֽ�  ������
        var fileType = arguments[1].fileType;//�ļ�����  ������
        var data = arguments[1].data;//��������
        var onStart = arguments[1].onStart;//׼���ϴ�
        var abortBtn = arguments[1].abortBtn;//�ж��ϴ���ť
        var onAbort = arguments[1].onAbort;//�жϺ���
        //�ļ�������֤
        var type = getFileType(files[0].name);//����ļ�����
        var typeAllow = false;
        if(typeof fileType == 'object')
        {
            var arrayType = fileType.type;
            $.each(arrayType,function(index,value){
                if(value.toUpperCase() == type)
                {
                    typeAllow = true;
                    return false;//�൱��break
                }
            });
            if(!typeAllow)
            {
                fileType.error(files[0],key);
                return false;
            }
        }
        //�ļ���С��֤
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
        //���ļ����뵽�ϴ�ͨ��
        formData.append(input.attr('name')||'files', files[0]);
        var xhr = new XMLHttpRequest();
        //���Ӷ��������
        if(data != '' && data != 'undefined')
        {
            $.each(data,function(index,value){
                formData.append(index,value);
            });
        }
        //��ʼ�ϴ�
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
        //����ж�
        $('.'+key).find(abortBtn).click(function(){
            xhr.abort();
            onAbort(files[0],key);
        });
        return {key:key,xhr:xhr};
    }
    else
    {
        alert('������汾����');
        return null;
    }
}