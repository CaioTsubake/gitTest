/**
 * 
 */
$(document).ready(function(){
	getBooks();
	
	function getBooks(){
		$.get("/books", function(books){
			$.each(books, function(index, book){
				$(".listing").append("<li><a>" + book.title + "</a></li>");
				$("a:not([href])").attr("href","/book/" + book.id);
			})
		});
	}
});