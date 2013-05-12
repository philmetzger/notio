<style>
	label {
		display: block;
	}
	textarea {
   		resize: none;
	}
</style>
<div style="display: inline-block; width: 100%; background-color: blue;">
	<div style="float: left; padding: 20px; margin-bottom: 1px;">
		<form action="create.html" method="post" style="">
			<div><label>Note: </label><input name="text" style="width: 421px; margin-right: 32px;"/></div>	
			<div style="float: left;"><label>Category: </label>
				<select name="category" style="width: 150px; margin-right: 32px;">
					<option value="personal">Personal</option>
					<option value="work">Work</option>
					<option value="family">Family</option>
				</select>
			</div>
			<input type="submit" value="Create" style="display:block; margin-top: 22px;"/>
		</form>
	</div>
	<div style="float: right; padding: 20px; margin-bottom: 1px;">
		<form action="search.html" method="post" style="float: left;">
			<select name="category" style="width: 150px; margin-right: 32px;">
				<option value="personal">Personal</option>
				<option value="work">Work</option>
				<option value="family">Family</option>
			</select>
			<input type="submit" value="Search">
		</form>
		<a style="float: left;" href="feed.html">Show all</a>
	</div>
</div>