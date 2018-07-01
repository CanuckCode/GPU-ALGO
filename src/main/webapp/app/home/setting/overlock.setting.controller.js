(function() {
    'use strict';

    angular
        .module('gpualgoApp')
        .controller('OverlockSettingController', OverlockSettingController);

    OverlockSettingController.$inject = ['$http', 'OverlockSettingService', 'parrentSetting', '$uibModalInstance'];

    function OverlockSettingController ($http, OverlockSettingService, parrentSetting, $uibModalInstance) {
        var vm = this;

        vm.cancel = cancel;
        vm.save = save;

        function save() {

            parrentSetting.memoryCapacity = vm.memoryCapacity;
            parrentSetting.coresNumber = vm.coresNumber;
            parrentSetting.powerCapacity = vm.powerCapacity;

            $http
                .post('/api/overlock/save', parrentSetting)
                .then(function (resp) {
                    $uibModalInstance.dismiss('cancel');
                }, function () {
                    alert('Failed!!!');
                })

        }

        function cancel () {
            $uibModalInstance.dismiss('cancel');
        }
    }

})();
