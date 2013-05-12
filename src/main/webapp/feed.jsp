<%@ include file="fragments/header.jsp" %>
<a href="j_spring_security_logout">
	Logout!
</a>
<h1><security:authentication property="principal.username" />, here are your current notes</h1>

<%@ include file="fragments/createFeedItem.jsp" %>

<c:if test="${not empty feedItems}">
	<c:forEach items="${feedItems}" var="feedItems" varStatus="row">
		<div style="clear: both; width: 100%; padding: 20px; background-color: green; margin-bottom: 1px;">
			${feedItems.text}<br/>
			${feedItems.category} &middot; ${feedItems.author}
			<form action="delete.html" method="post">
				<input name="feedItemId" value="${feedItems.id}" type="hidden"> 
				<input type="submit" value="Delete">
			</form>
		</div>
	</c:forEach>
</c:if>
<c:if test="${empty feedItems}">
	<p>No notes.</p>
</c:if>

<%@ include file="fragments/footer.jsp" %>