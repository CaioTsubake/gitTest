/**
 * 
 */

$(document).ready(function(){

	getComments();
	
	function getComments() {
		var url = window.location.href;
		var array = url.split("/")
		var id = array[4];
	
		$.get("/bookComments/" + id, function(results) {
			console.log(results)
			$.each(results, function(index, result){
				var item = $("<li>"+ result.author.username + " posted:" + "<br/>" + result.content +"</li>")
				$(".commentsList").append(item);
			})
		})
	}
	
})