angular.module('ngBoilerplate.user',['ui.router','ngResource'])
.config(function($stateProvider){
$stateProvider.state("login",{
   url:'/login',
           views: {
               'main': {
                   templateUrl:'user/login.tpl.html',
                   controller:'LoginCtrl'
               }
           },
           data : { pageTitle : "Login" }
})
    .state("register",{
    url:'/register',
            views:{
                'main':{
                    templateUrl:'user/register.tpl.html',
                    controller:'RegisterCtrl'
                }
            },
            data : {pageTitle : "Registration"}
 })
 .state('itemSearch', {
             url:'/items/search',
             views: {
                 'main': {
                     templateUrl:'user/search.tpl.html',
                     controller: 'ItemSearchCtrl'
                 }
             },
             data : { pageTitle : "Search Items" },
             resolve: {
                 items: function(itemService) {
                     return itemService.getAllItems();
                 }
             }
     });
 .factory("sessionService",function(){
    var session = {};
    session.login = function(data){
        return $http.post("/OrderingSystemWebApp/login", "username=" + data.name +
                "&password=" + data.password, {
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                } ).then(function(data) {
                    alert("login successful");
                    localStorage.setItem("session", {});
                }, function(data) {
                    alert("error logging in");
                });
    };
    session.logout = function(data){
            alert("user logged out");
            localStorage.removeItem("session");
        };
    session.isLogedIn = function(data){
            return localStorage.getItem("session") !== null;
        };
    return session;
 })
 .factory('orderService', function($resource) {
     var service = {};
     return service;
 })
 .factory('userService',function($resouce){
    var service = {};
    service.register = function(user,success,failure){
        var User = $resouce("/OrderingSystemWebApp/rest/users");
        User.save({},user,success,failure);
    };
    service.getUserById = function(userId) {
            var User = $resource("/OrderingSystemWebApp/rest/users/:paramUserId");
            return User.get({paramUserId:userId}).$promise;
        };
    service.userExists = function(user,success,failure){
        var User = $resouce("/OrderingSystemWebApp/rest/users");
        var data = User.get({email:user.email},
                                function(){
                                    var users = data.users;
                                    if (users.length !== 0){
                                        success(users[0]);
                                    }else{
                                        failure();
                                    }
                                },
                                }
                                failure);
    service.getAllUsers = function() {
              var User = $resource("/OrderingSystemWebApp/rest/users");
              return User.get().$promise.then(function(data) {
                return data.users;
              });
          };
    return service;
 })
 .controller("LoginCtrl", function($scope, sessionService, userService, $state) {
     $scope.login = function() {
         userService.userExists($scope.user, function(user) {
             sessionService.login($scope.user).then(function() {
                 $state.go("home");
             });
         },
         function() {
             alert("Error logging in user");
         });
     };
 })
 .controller("RegisterCtrl", function($scope, sessionService, $state, userService) {
     $scope.register = function() {
         userService.register($scope.user,
         function(returnedData) {
             sessionService.login($scope.user).then(function() {
                 $state.go("home");
             });
         },
         function() {
             alert("Error registering user");
         });
     };
 })
 .controller("ItemSearchCtrl", function($scope, items) {
     $scope.items = items;
 });
