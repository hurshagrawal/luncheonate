// Generated by CoffeeScript 1.6.2
(function() {
  $('.js-find-venues').on('click', function() {
    var noLocationSupport, onLocationFound;

    onLocationFound = function(pos) {
      return window.location = "/venues?lat=" + pos.coords.latitude + "&long=" + pos.coords.longitude;
    };
    noLocationSupport = function() {
      return $('#no-location-error').show();
    };
    if (navigator.geolocation) {
      return navigator.geolocation.getCurrentPosition(onLocationFound, noLocationSupport);
    } else {
      return noLocationSupport();
    }
  });

}).call(this);
