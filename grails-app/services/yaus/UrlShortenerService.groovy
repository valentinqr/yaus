package yaus

class UrlShortenerService {

	static transactional = true

	def grailsApplication
	def sequenceGenerator = new SequenceGenerator()

	// Generate and return a unique identifier for a url and store it associated to the url
    def shortUrl(longUrl){
    	def shortener = UrlShortener.findByLongUrl(longUrl)

    	if (shortener)
    		return shortener.shortUrl
    	else {
    		def urlRest = sequenceGenerator.getNextSequence().toString()
    		def instance = new UrlShortener(shortUrl: urlRest, longUrl: longUrl)
    		instance.save()
    		
    		instance.hasErrors()?null:urlRest
    	}
    }

    // Generates a short url and return it with the domain
    def shortUrlWithDomain(longUrl){
    	def urlRest = shortUrl(longUrl)
    	urlRest?createShortLink(urlRest):null
    }

    def findLongUrlBy(shortUrlWithDomain){
        def urlRest = shortUrlWithDomain - grailsApplication.config.domainName
    	UrlShortener.findByShortUrl(urlRest).longUrl
    }

    private createShortLink(urlRest) {
    	def domain = grailsApplication.config.domainName
    	return "${domain}${urlRest}"
    }
}
