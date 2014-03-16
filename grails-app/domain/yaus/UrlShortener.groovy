package yaus

class UrlShortener {

    String shortUrl
    String longUrl
    
    static constraints = {
           shortUrl size:1..25, matches:"[0-9]+", unique:true
           longUrl 	size:1..255, url:true, unique: true
    } 
}
