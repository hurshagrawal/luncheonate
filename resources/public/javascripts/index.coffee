# Homepage
$('.js-find-venues').on 'click', ->
  onLocationFound = (pos) ->
    latlong = encodeURIComponent("#{pos.coords.latitude},#{pos.coords.longitude}")
    window.location = "/venues?ll=#{latlong}"

  noLocationSupport = ->
    $('#no-location-error').show()

  if navigator.geolocation
    navigator.geolocation.getCurrentPosition(onLocationFound, noLocationSupport)
  else
    noLocationSupport()
