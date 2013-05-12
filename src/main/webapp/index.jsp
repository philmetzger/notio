<%@ include file="fragments/header.jsp" %>

<h1>Welcome to Notio.</h1>
<p>Create and save quick notes.</p>

<c:if test="${not empty signedUp}">
	<p>You have successfully signup!</p>
</c:if>
<h3>Login <a href="feed.html">here.</a></h3>

<c:if test="${empty signedUp}">
	<br/><br/>
	<h3>Sign up below</h3>
	<form action="createAccount.html" method="post">
		<div><label>Username: </label><input name="username" autocomplete="off" style="width: 421px; margin-right: 32px;"/></div>	
		<div><label>Password: </label><input name="password" type="password" autocomplete="off" style="width: 421px; margin-right: 32px;"/></div>	
		<input type="submit" value="Sign up">
	</form>
</c:if>

<%@ include file="fragments/footer.jsp" %>