
var app=angular.module("queryapp",[]);
app.controller('querycontroller', {
        templateUrl: '../devicequery.html',
        controller: function querycontroller($http) {
            var self = this;
            self.orderProp = 'age';

            $http.get('json/information.json').then(function(response) {
                self.devices = response.data;
            });
        }
    });