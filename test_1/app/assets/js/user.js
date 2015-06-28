/**
 * 
 */

$(document).ready(function() {
	
	
	getComments();
	
	function getComments() {
		$.get("/comments", function(results) {
			$.each(results, function(index, result){
				var item = $("<li>"+ result.author.username + " posted:" + "<br/>" + result.content +"</li>")
				$(".commentsList").append(item);
			})
		})
	}
	
	function insertButton() {
		
	}
	
})