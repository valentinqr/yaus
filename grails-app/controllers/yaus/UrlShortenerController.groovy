package yaus

class UrlShortenerController {

    static allowedMethods = [ 
                                redirectToLongUrlFrom: "GET",
                                shortUrlWithDomain: "POST"
                            ]


	def urlShortenerService

    
    public redirectToLongUrlFrom() {
        def longUrl = urlShortenerService.findLongUrlBy(params.shortUrl)

        if (longUrl) {
            redirect url:longUrl
        } else {
            response.status = 404
        }
    }

    public shortUrlWithDomain() {
        def shortUrl = urlShortenerService.shortUrlWithDomain(params.longUrl)
        render(view:'/urlShortener', model:[shortUrl: shortUrl])
    }
   
}
