/**
 * Created by ionut on 6/2/2017.
 */
(function (module) {

    var alertService = function ($http) {

        var headers = {
            'Access-Control-Allow-Origin' : '*',
            'Access-Control-Allow-Methods' : 'POST, GET, PUT',
            'Accept': 'application/json'
        };

        var param_check = function(lat, lon, radius, since_date, until_date) {
            if (lat == undefined || lat == null ||
                lon == undefined || lon == null ||
                radius == undefined || radius == null ||
                since_date == undefined || since_date == null ||
                until_date == undefined || until_date == null )

                window.alert("No location selected!");
        };

        //radius e cu double si in transformam in int
        var getLocationTwitter = function (lat, lon, radius, since_date, until_date, searchTags) {
            param_check(lat, lon, radius, since_date, until_date);

            var url = "http://localhost:8080/tweets/locationAndDate?" +
                "lat=" + lat + "&" + "lon=" + lon  + "&res=" + radius +
                "&since=" + since_date + "&until=" + until_date + "&searchTags=" + searchTags;

            return $http({
                url: url,
                method: 'GET',
                headers: headers
            });
        };


        var submitFormService = function (nume, prenume, initialaTata, cnp, adresa, judet, localitate, telefon,
        email, tipVenit, codIdentificare, cont, salariuBrut, sumaTotala) {
            var url = "http://localhost:8080/formular?nume=" + nume + "&" +
                    "prenume=" + prenume + "&" +
                    "initialaTata=" + initialaTata + "&" +
                    "cnp=" + cnp + "&" +
                "adresa=" + adresa + "&" +
                "judet=" + judet + "&" +
                "localitate=" + localitate + "&" +
                "telefon=" + telefon + "&" +
                "email=" + email + "&" +
                "tipVenit=" + tipVenit + "&" +
                "codIdentificare=" + codIdentificare + "&" +
                "cont=" + cont + "&" +
                "salariuBrut=" + salariuBrut + "&" +
                "sumaTotala=" + sumaTotala;

            return $http({
                url: url,
                method: 'POST',
                headers: headers
            });
        };


        return {
            getLocationTwitter: getLocationTwitter,
            submitFormService: submitFormService,
        };
    };

    module.factory("alertService", alertService);
}(angular.module("RDash")));
