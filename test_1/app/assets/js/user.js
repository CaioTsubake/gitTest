/**
 * 
 */

$(document).ready(function() {
	
	console.log("JS start");
	
	getComments();
	
	function getComments () {
		$.get("/comments", function(results) {
			$.each(results, function(index, result){
				console.log(result);
				$(".commentsList").append("<li>"+ result.author.username + " posted:" + "<br/>"
											+ result.content +"</li>");
			})
		})
	}
	
})