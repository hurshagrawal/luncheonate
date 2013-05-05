# Homepage
$('.js-find-venues').on 'click', ->
  onLocationFound = (pos) ->
    window.location = "/venues?lat=#{pos.coords.latitude}&long=#{pos.coords.longitude}"

  noLocationSupport = ->
    $('#no-location-error').show()

  if navigator.geolocation
    navigator.geolocation.getCurrentPosition(onLocationFound, noLocationSupport)
  else
    noLocationSupport()
