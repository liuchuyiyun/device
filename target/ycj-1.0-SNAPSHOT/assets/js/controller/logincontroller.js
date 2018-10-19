/**
 * Created by lx on 2016/11/8.
 */
var app=angular.module("loginapp",[]);
app.controller("logincontroller",function($scope,$http){
    $scope.login=function(){
        $http.get("json/user.json").then(function(response){
            for(var i=0;i<response.data.length;i++){
                if(($scope.username==response.data[i].username)&&($scope.password==response.data[i].password)){
                    $scope.panduan=true;
                }
            }
            if($scope.panduan==true){
                location.href="../../../index.html";
            }else{
                alert("用户名密码错误");
            }
        });
        };
});