(function() {
    'use strict';

    angular
        .module('gpualgoApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', 'Principal', 'LoginService', '$state', 'OverlockService', '$http', 'OverlockSettingService', '$timeout'];

    function HomeController ($scope, Principal, LoginService, $state, OverlockService, $http, OverlockSettingService, $timeout) {
        var vm = this;

        vm.totalItems = 0;
        vm.currentPage = 1;
        vm.numberPerPage = 5;
        vm.data = [];

        vm.sortType     = 'vm.rank'; // set the default sort type
        vm.sortIt  = false;  // set the default sort order
        vm.searchTerm   = '';

        vm.overlockOptions = {};
        vm.allowToAddNewSetting = false;

        vm.showVoteWarning = false;

        vm.openNewSetting = OverlockSettingService.open;

        vm.overlockSettings = [];

        vm.architectures = OverlockService.gpuArchitecture;
        vm.algorithms = OverlockService.algorithm;
        vm.brands = OverlockService.gpuBrand;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.enableBrand = false;
        vm.enableModel = false;
        vm.enableAlgorithm = false;

        vm.search = function () {
            vm.overlockOptions.searchCondition = vm.searchTerm;

            OverlockSettingService
                .findSettings(vm.overlockOptions, vm.currentPage, vm.numberPerPage)
                .success(function (data) {
                    vm.data = data.content;
                    var startPosition = vm.currentPage * vm.numberPerPage - 5;
                    for (var i = 0, length = vm.data.length; i < length; i++) {
                        var e = vm.data[i];
                        e.position = ++startPosition;
                    }
                    vm.totalItems = data.totalElements;
                });
        };

        vm.sort = function (sortType) {
            vm.sortType = sortType;
            vm.sortIt = !vm.sortIt;

            vm.overlockOptions.sortProperty = vm.sortType;
            vm.overlockOptions.sortIt = vm.sortIt ? 'desc' : 'asc';


            OverlockSettingService
                .findSettings(vm.overlockOptions, vm.currentPage, vm.numberPerPage)
                .success(function (data) {
                    vm.data = data.content;
                    var startPosition = vm.currentPage * vm.numberPerPage - 5;
                    for (var i = 0, length = vm.data.length; i < length; i++) {
                        var e = vm.data[i];
                        e.position = ++startPosition;
                    }
                    vm.totalItems = data.totalElements;
                });
        };

        vm.pageChanged = function () {
            OverlockSettingService
                .findSettings(vm.overlockOptions, vm.currentPage, vm.numberPerPage)
                .success(function (data) {
                    vm.data = data.content;
                    var startPosition = vm.currentPage * vm.numberPerPage - 5;
                    for (var i = 0, length = vm.data.length; i < length; i++) {
                        var e = vm.data[i];
                        e.position = ++startPosition;
                    }
                    vm.totalItems = data.totalElements;
                });
        };

        vm.loadByGpuArchitecture = function () {
            vm.enableBrand = true;
            vm.enableModel = false;
            vm.enableAlgorithm = false;

            OverlockSettingService
                .findSettings(vm.overlockOptions, vm.currentPage, vm.numberPerPage)
                .success(function (data) {
                    vm.data = data.content;
                    var startPosition = vm.currentPage * vm.numberPerPage - 5;
                    for (var i = 0, length = vm.data.length; i < length; i++) {
                        var e = vm.data[i];
                        e.position = ++startPosition;
                    }
                    vm.totalItems = data.totalElements;
                    vm.allowToAddNewSetting = false;
                    vm.overlockOptions.model = '';
                    vm.overlockOptions.algorithm = '';
                    vm.overlockOptions.brand = '';
                });
        };

        vm.loadByGpuBrand = function() {
            vm.models = OverlockService.gpuModel[vm.overlockOptions.brand];
            OverlockSettingService
                .findSettings(vm.overlockOptions, vm.currentPage, vm.numberPerPage)
                .success(function(data){
                    vm.data = data.content;
                    var startPosition = vm.currentPage * vm.numberPerPage - 5;
                    for (var i = 0, length = vm.data.length; i < length; i++) {
                        var e = vm.data[i];
                        e.position = ++startPosition;
                    }
                    vm.totalItems = data.totalElements;
                    vm.enableModel = true;
                    vm.enableAlgorithm = false;
                    vm.allowToAddNewSetting = false;
                    vm.overlockOptions.algorithm = '';
                    vm.overlockOptions.model = '';
                });
        };

        vm.loadByGpuModel = function () {
            vm.enableAlgorithm = true;
            OverlockSettingService
                .findSettings(vm.overlockOptions, vm.currentPage, vm.numberPerPage)
                .success(function(data){
                    vm.data = data.content;
                    var startPosition = vm.currentPage * vm.numberPerPage - 5;
                    for (var i = 0, length = vm.data.length; i < length; i++) {
                        var e = vm.data[i];
                        e.position = ++startPosition;
                    }
                    vm.totalItems = data.totalElements;
                    vm.allowToAddNewSetting = false;
                    vm.overlockOptions.algorithm = '';
                });
        };

        vm.loadByAlgorithm = function () {
            OverlockSettingService
                .findSettings(vm.overlockOptions, vm.currentPage, vm.numberPerPage)
                .success(function(data){
                    vm.data = data.content;
                    var startPosition = vm.currentPage * vm.numberPerPage - 5;
                    for (var i = 0, length = vm.data.length; i < length; i++) {
                        var e = vm.data[i];
                        e.position = ++startPosition;
                    }
                    vm.totalItems = data.totalElements;
                    vm.allowToAddNewSetting = true;
                });
        };

        vm.voteUp = function (id) {
            $http.get("/api/overlock/vote/up", {params: {id: id}}).success(function (resp) {
                if (resp !== '' && !resp.success) {
                    vm.showVoteWarning = true;
                    $timeout(function() {
                        vm.showVoteWarning = false;
                    }, 3000);
                } else {
                    OverlockSettingService
                        .findSettings(vm.overlockOptions, vm.currentPage, vm.numberPerPage)
                        .success(function(data){
                            vm.data = data.content;
                            var startPosition = vm.currentPage * vm.numberPerPage - 5;
                            for (var i = 0, length = vm.data.length; i < length; i++) {
                                var e = vm.data[i];
                                e.position = ++startPosition;
                            }
                            vm.totalItems = data.totalElements;
                        });
                }
            });
        };

        vm.voteDown = function (id) {
            $http.get("/api/overlock/vote/down", {params: {id: id}}).success(function (resp) {
                if (resp !== '' && !resp.success) {
                    vm.showVoteWarning = true;
                    $timeout(function() {
                        vm.showVoteWarning = false;
                    }, 3000);
                } else {
                    OverlockSettingService
                        .findSettings(vm.overlockOptions, vm.currentPage, vm.numberPerPage)
                        .success(function(data){
                            vm.data = data.content;
                            var startPosition = vm.currentPage * vm.numberPerPage - 5;
                            for (var i = 0, length = vm.data.length; i < length; i++) {
                                var e = vm.data[i];
                                e.position = ++startPosition;
                            }
                            vm.totalItems = data.totalElements;
                        });
                }
            });
        };

        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }

        function register () {
            $state.go('register');
        }

    }

})();
