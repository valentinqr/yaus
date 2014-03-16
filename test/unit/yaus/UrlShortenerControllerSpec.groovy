package yaus

import spock.lang.*
import grails.test.mixin.*

@TestFor(UrlShortenerController)
@Mock(UrlShortener)
class UrlShortenerControllerSpec extends Specification {

	def urlShortenerServiceMock
	static SHORT_URL = '123456'
	static ANOTHER_SHORT_URL = '789123'
	static LONG_URL = 'http://www.long-url.com'
	static INVALID_URL = 'assjajnjn%dd'


	void setup() {
		urlShortenerServiceMock = Mock(UrlShortenerService)
		controller.urlShortenerService = urlShortenerServiceMock
	}

	def "test can save new urls"() {

		setup:
			def instance = new UrlShortener(shortUrl: SHORT_URL, longUrl: LONG_URL).save()

		expect:
			assert (instance.validate())
			assertEquals (0,instance.errors.getErrorCount())
	}

	def "test can not save invalid long urls"() {

		setup:
			def instance = new UrlShortener(shortUrl: SHORT_URL, longUrl: INVALID_URL).save()

		expect:
			assert (!instance)
	}

	def "test should redirect to the long url given a short one"() {

		setup:
			1 * urlShortenerServiceMock.findLongUrlBy(_) >> 'my_long_url'

		when:
			controller.redirectToLongUrlFrom()

		then:
			controller.response.redirectedUrl == 'my_long_url'
	}

	def "test shoud not redirect and return with status 404 when do not know the long url"() {

		given:
			1 * urlShortenerServiceMock.findLongUrlBy(_) >> null

		when:
			controller.redirectToLongUrlFrom()

		then:
			controller.response.status == 404
	}
}