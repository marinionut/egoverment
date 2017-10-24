/**
 * Alerts Controller
 */

angular
    .module('RDash')
    .controller('AlertsCtrl', ['$scope', '$http', 'alertService', AlertsCtrl]);

function AlertsCtrl($scope, $http, alertService) {
    $scope.model = {
        nume : "",
        prenume : "",
        initialaTata : "",
        cnp : "",
        adresa : "",
        judet : "",
        localitate : "",
        telefon : "",
        email : "",
        tip_venit : "",
        cod_identificare : "",
        cont : "",
        brut : "",
        sumaTotala : ""
    };
    $scope.isOk = true;

    $scope.pdfAbsolutePath = {};

    $scope.calculSumaDonatie = function () {
        $scope.model.sumaTotala = ($scope.model.brut * 12 * 0.02).toFixed(2);
    };

    $scope.clearResults = function () {
        $scope.isOk = true;
        $scope.warningList = [];
        $scope.model = {
            nume : "",
            prenume : "",
            initialaTata : "",
            cnp : "",
            adresa : "",
            judet : "",
            localitate : "",
            telefon : "",
            email : "",
            tip_venit : "",
            cod_identificare : "",
            cont : "",
            brut : "",
            sumaTotala : ""
        };
    };

    $scope.pdfId = {};

    $scope.submitForm = function () {
        alertService.submitFormService($scope.model.nume, $scope.model.prenume,
            $scope.model.initialaTata, $scope.model.cnp, $scope.model.adresa,
            $scope.model.judet, $scope.model.localitate, $scope.model.telefon,
            $scope.model.email, $scope.model.tip_venit, $scope.model.cod_identificare,
            $scope.model.cont, $scope.model.brut, $scope.model.sumaTotala).success(function (data) {
            $scope.pdfPath = data;
            console.log($scope.pdfPath);
        });
    };

    $scope.submit = function() {
        $scope.isOk = true;
        //$scope.validateForm();
        if ($scope.isOk == true) {
            console.log($scope.model);
            $scope.warningList = [];
            $scope.submitForm();
        };
    };



    $scope.validateForm = function() {
        $scope.warningList = [];

        if($scope.model.nume == "") {
            $scope.isOk = false;
        }
        if($scope.model.prenume == "") {
            $scope.isOk = false;
        }
        if($scope.model.initialaTata == "") {
            $scope.isOk = false;
        }
        if($scope.model.cnp == "") {
            $scope.isOk = false;
        }
        if($scope.model.adresa == "") {
            $scope.isOk = false;
        }
        if($scope.model.judet == "") {
            $scope.isOk = false;
        }
        if($scope.model.localitate == "") {
            $scope.isOk = false;
        }
        // if($scope.model.telefon == "") {
        //     $scope.isOk = false;
        // }
        if($scope.model.tip_venit == "") {
            $scope.isOk = false;
        }
        if($scope.model.cod_identificare == "") {
            $scope.isOk = false;
        }
        if($scope.model.cont == "") {
            $scope.isOk = false;
        }
        if($scope.model.brut == "") {
            $scope.isOk = false;
        }

        if($scope.is_valid_cnp($scope.model.cnp) == false) {
            $scope.isOk = false;
            $scope.warningList.push("Cnp-ul nu este valid!");
        }
        if($scope.model.cont != "") {
            if($scope.isValidIBANNumber($scope.model.cont) == false) {
                $scope.isOk = false;
                $scope.warningList.push("Contul IBAN destinatie nu este valid!");
            }
        }
        if($scope.model.email != "") {
            if ($scope.emailValidator($scope.model.email) == false) {
                $scope.isOk = false;
                $scope.warningList.push("Email-ul nu este valid!");
            }
        }

    };


    $scope.alerts = [{
        type: 'success',
        msg: 'Bun venit!'
    }];

    $scope.closeAlert = function(index) {
        $scope.alerts.splice(index, 1);
    };

    $scope.emailValidator = function (email) {
        var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    };

    $scope.is_valid_cnp = function is_valid_cnp(value) {
        // 0: cnp
        // 1: s (1-8)
        // 2: aa (00-99)
        // 3: ll (01-12)
        // 4: zz (01-31)
        // 5: jj (1-46, 51-52)
        // 6: nnn (001-999)
        // 7: c (279146358279)
        var m;
        if (m = /^([1-8])(0[1-9]|[1-9][0-9])(0[1-9]|1[0-2])(\d{2})(\d{2})(\d{3})(\d)$/.exec(value)) {
            var ll = parseInt(m[3])
            var zz = parseInt(m[4])
            switch (ll) {
                case 2:
                    if (zz > 29) {
                        return false
                    }
                case 3:
                case 4:
                case 6:
                case 9:
                case 11:
                    if (zz > 30) {
                        return false
                    }
                default:
                    if (zz > 31) {
                        return false
                    }
            }
            var jj = parseInt(m[5])
            if (jj < 0 || (jj > 46 && jj < 51) || jj > 52) {
                return false
            }
            var nnn = parseInt(m[6])
            if (nnn < 0) {
                return false
            }
            var c = parseInt(m[7])
            var constanta = "279146358279"
            var suma = 0
            for(var i = 0; i < constanta.length; i++) {
                suma = suma + parseInt(m[0].charAt(i)) * constanta.charAt(i)
            }
            var rest = suma % 11
            return (((rest < 10) && (rest == m[0].charAt(12))) || ((rest == 10) && (m[0].charAt(12) == 1))) ? true : false
        }
        return false
    };

    $scope.isValidIBANNumber = function (input) {
            var CODE_LENGTHS = {
                AD: 24, AE: 23, AT: 20, AZ: 28, BA: 20, BE: 16, BG: 22, BH: 22, BR: 29,
                CH: 21, CR: 21, CY: 28, CZ: 24, DE: 22, DK: 18, DO: 28, EE: 20, ES: 24,
                FI: 18, FO: 18, FR: 27, GB: 22, GI: 23, GL: 18, GR: 27, GT: 28, HR: 21,
                HU: 28, IE: 22, IL: 23, IS: 26, IT: 27, JO: 30, KW: 30, KZ: 20, LB: 28,
                LI: 21, LT: 20, LU: 20, LV: 21, MC: 27, MD: 24, ME: 22, MK: 19, MR: 27,
                MT: 31, MU: 30, NL: 18, NO: 15, PK: 24, PL: 28, PS: 29, PT: 25, QA: 29,
                RO: 24, RS: 22, SA: 24, SE: 24, SI: 19, SK: 24, SM: 27, TN: 24, TR: 26
            };
            var iban = String(input).toUpperCase().replace(/[^A-Z0-9]/g, ''), // keep only alphanumeric characters
                code = iban.match(/^([A-Z]{2})(\d{2})([A-Z\d]+)$/), // match and capture (1) the country code, (2) the check digits, and (3) the rest
                digits;
            // check syntax and length
            if (!code || iban.length !== CODE_LENGTHS[code[1]]) {
                return false;
            }
            // rearrange country code and check digits, and convert chars to ints
            digits = (code[3] + code[1] + code[2]).replace(/[A-Z]/g, function (letter) {
                return letter.charCodeAt(0) - 55;
            });
            // final check
            return mod97(digits);
    }
    function mod97(string) {
        var checksum = string.slice(0, 2), fragment;
        for (var offset = 2; offset < string.length; offset += 7) {
            fragment = String(checksum) + string.substring(offset, offset + 7);
            checksum = parseInt(fragment, 10) % 97;
        }
        return checksum;
    }
}