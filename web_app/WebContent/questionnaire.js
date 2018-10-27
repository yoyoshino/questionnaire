$(function() {

	var s = 0;
	$('#describe').on('click', function() {
		if (s == 0) {
			$('#describe-text').fadeIn(2000);
			s++;
		} else {
			$('#describe-text').css('display', 'none');
			s--;
		}
	});

	$('#result').on('click', function() {
		$('#result').fadeOut();
		$('#past-num').fadeIn();
		$('#pre-piechart').css('visibility', 'visible');
	});
});