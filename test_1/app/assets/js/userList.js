/**
 * 
 */
$(document).ready(function() {
	getUsers();
	
	function getUsers(){
		$.get("/users", function(users){
			$.each(users, function(index, user) {
				var route = "/user/" + user.id
				$(".listing").append("<li><a>" + user.username + "</a></li>");
				$("a:not([href])").attr("href", route);
			})
			
		});
	}
	
});