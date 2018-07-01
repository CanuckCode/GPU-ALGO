(function() {
    'use strict';

    angular
        .module('gpualgoApp')
        .factory('OverlockSettingService', OverlockSettingService);

    OverlockSettingService.$inject = ['$uibModal', '$http'];

    function OverlockSettingService ($uibModal, $http) {
        var service = {
            open: open,
            findSettings: findSettings
        };

        var modalInstance = null;
        var resetModal = function () {
            modalInstance = null;
        };

        return service;

        function open (parrentSettings) {
            if (modalInstance !== null) return;
            modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/home/setting/newSetting.html',
                controller: 'OverlockSettingController',
                controllerAs: 'vm',
                resolve: {
                    parrentSetting: function () {
                        return parrentSettings;
                    }
                }
            });
            modalInstance.result.then(
                resetModal,
                resetModal
            );
        }

        function findSettings(settingInfo, pageNumber, size) {
            settingInfo.page = pageNumber;
            settingInfo.size = size;
            return $http.get('/api/overlock/findSettings', {params: settingInfo});
        }

    }
})();
