/**
 * 유효성 검사
 */
function isValid() {

  	const form = document.getElementById('form');

	if (!form.title.value.trim()) {
		alert('제목을 입력해 주세요.');
		form.title.value = '';
		form.title.focus();
		return false;
	}

	if (!form.writer.value.trim()) {
		alert('작성자를 입력해 주세요.');
		form.writer.value = '';
		form.writer.focus();
		return false;
	}

	if (!form.content.value.trim()) {
		alert('내용을 입력해 주세요.');
		form.content.value = '';
		form.content.focus();
		return false;
	}

	return true;
}