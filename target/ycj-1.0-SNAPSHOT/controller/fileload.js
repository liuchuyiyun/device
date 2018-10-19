/**
 * Created by lx on 2016/11/9.
 */
var app=angular.module("fileload",[]);
app.controller('controlName', ['$scope', '$http', function($scope, $http) {
    $scope.reader = new FileReader();   //����һ��FileReader�ӿ�
    $scope.form = {     //���ڰ��ύ���ݣ�ͼƬ����������
        image:{}
    };
    $scope.thumb = {};      //���ڴ��ͼƬ��base64
    $scope.thumb_default = {    //����ѭ��Ĭ�ϵġ��Ӻš����ͼƬ�Ŀ�
        1111:{}
    };

    $scope.img_upload = function(files) {       //�����ύͼƬ�ĺ���
        $scope.guid = (new Date()).valueOf();   //ͨ��ʱ�������һ�����������Ϊ����ʹ��
        $scope.reader.readAsDataURL(files[0]);  //FileReader�ķ�������ͼƬת��base64
        $scope.reader.onload = function(ev) {
            $scope.$apply(function(){
                $scope.thumb[$scope.guid] = {
                    imgSrc : ev.target.result,  //����base64
                }
            });
        };

        var data = new FormData();      //����Ϊ���̨�ύͼƬ����
        data.append('image', files[0]);
        data.append('guid',$scope.guid);
        $http({
            method: 'post',
            url: '/comm/test-upload.php?action=success',
            data:data,
            headers: {'Content-Type': undefined},
            transformRequest: angular.identity
        }).success(function(data) {
            if (data.result_code == 'SUCCESS') {
                $scope.form.image[data.guid] = data.result_value;
                $scope.thumb[data.guid].status = 'SUCCESS';
                console.log($scope.form)
            }
            if(data.result_code == 'FAIL'){
                console.log(data)
            }
        })
    };

    $scope.img_del = function(key) {    //ɾ����ɾ����ʱ��thumb��form�����ͼƬ���ݶ�Ҫɾ���������ύ����Ҫ��
        var guidArr = [];
        for(var p in $scope.thumb){
            guidArr.push(p);
        }
        delete $scope.thumb[guidArr[key]];
        delete $scope.form.image[guidArr[key]];
    };
    $scope.submit_form = function(){    //ͼƬѡ����Ϻ���ύ������ύ��û���ύǰ���ͼƬ���ݣ�ֻ���ύ�û�������Ϻ�
        $http({
            method: 'post',
            url: '/comm/test.php',
            data:$scope.form,
        }).success(function(data) {
            console.log(data);
        })
    };
}]);