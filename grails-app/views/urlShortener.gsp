<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<r:require module="shortener"/>
		<title><g:message code="shortener.title"/></title>
	</head>
	<body>
		<div id="page-body" role="main" class="formulario">
			<h1><g:message code="shortener.subtitle"/></h1>
			<p><g:message code="shortener.info"/></p>
		</div>
		<div class="formulario">
			<g:formRemote 	name="urlForm" on404="alert('not found!')" update="shortUrlField"
							url="[controller:'UrlShortener', action:'shortUrlWithDomain']">
				<input type="url" name="longUrl"/>
				<g:actionSubmit class="submitButton" value="Short it!" action="shortUrlWithDomain" />
			</g:formRemote>
		</div>
		<g:if test="${shortUrl}">
			<div id="shortUrlField" class="formulario">
				<p><g:message code="shortener.result.text"/></p>
				<g:link controller="UrlShortener" action="redirectToLongUrlFrom"
						params="[shortUrl:"${shortUrl}"]" absolute="true"> ${shortUrl}
				</g:link>
			</div>
		</g:if>
		<g:else>
			<p> </p>
		</g:else>
	</body>
</html>