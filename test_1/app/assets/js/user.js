/**
 * 
 */

$(document).ready(function() {
	
	
	getComments();
	hideFollowButton();
	
	function getComments() {
		var url = window.location.href;
		var array = url.split("/")
		var id = array[4];
	
		$.get("/comments/" + id, function(results) {
			$.each(results, function(index, result){
				var item = $("<li>"+ result.author.username + " posted:" + "<br/>" + result.content +"</li>")
				$(".commentsList").append(item);
			})
		})
	}
	
	function hideFollowButton(){
		var isOwnUser = true;
		
		if (isOwnUser === true){
			$("#followBtn").hide();
		}
	}
	

})