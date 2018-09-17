var chkInput = function(){
	var inputObjs = $(".commonForm input");
	var bEmpty = true;
	var focus;

	inputObjs.each(function(index){
		if($(this).val() == ''){
			focus = $(this);
			bEmpty = false;

			alert($(this).attr('title') + '은 필수 입력사항입니다.');
			focus.focus();

			return false;
		}
	});
	if( !bEmpty ) return false;
	else return true;
	//체크박스 선택도 추가하기
};