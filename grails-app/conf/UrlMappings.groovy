class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/urlShortener")
        "500"(view:'/error')
        "404"(view:'/error')
	}
}
