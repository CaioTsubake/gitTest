/**
 * 
 */

$(document).ready(function() {
	
	
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