package yaus

import spock.lang.*
import grails.test.spock.IntegrationSpec

class UrlShortenerServiceIntegrationSpec extends IntegrationSpec {

    def urlShortenerService

    def "test can short a long url"() {
        
        when:
            def shortUrl = urlShortenerService.shortUrl(longUrl)

        then:
            shortUrl == "111112"

        where:
            longUrl = "http://www.osoco.es"
    }

    def "test can generate the complete short url"(){

    	given:
    		def longUrl = "http://osoco.com/lolailole"

    	when:
    		def completeShortUrl = urlShortenerService.shortUrlWithDomain(longUrl)

    	then:
    		completeShortUrl == "http://my-yaus.com/111113"
    }

	def "test can short a previous shorted url"() {

		given:
			def previouslyShortedUrl = urlShortenerService.shortUrl(longUrl)

    	when:
            def shortUrl = urlShortenerService.shortUrl(longUrl)

        then:
            previouslyShortedUrl == shortUrl

        where:
            longUrl = "http://www.osoco.es"
    }

    def "test can short a previous shorted url with domain"(){

    	given:
			def previouslyShortedUrl = urlShortenerService.shortUrlWithDomain(longUrl)

    	when:
            def shortUrl = urlShortenerService.shortUrlWithDomain(longUrl)

        then:
            previouslyShortedUrl == shortUrl

        where:
            longUrl = "http://www.meneame.net"
    }

    def "test can return the long url associated with a short url"(){

    	given:
			def previouslyShortedUrl = urlShortenerService.shortUrlWithDomain(longUrl)

		when:
			def searchUrl = urlShortenerService.findLongUrlBy(previouslyShortedUrl)

		then:
			searchUrl == longUrl

		where:
			longUrl = "http://www.finofilipino.org" 
    }
}
