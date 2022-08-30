//제출 버튼 눌렀을때
const formBtn = document.querySelector('#formBtn');
formBtn.addEventListener('click', async (e) => {
	e.preventDefault();
	const name = document.querySelector('#name').value;
	const age = document.querySelector('#age').value;
	const height = document.querySelector('#height').value;
	const weight = document.querySelector('#weight').value;
	const muscle = document.querySelector('#muscle').value;
	const fat = document.querySelector('#fat').value;
	const sex = [...document.querySelectorAll('[name="sex"]')].find((el) => el.checked).value;
	// if (!age || !height || !weight || !muscle || !fat) {
	// 	alert('정보를 입력해주세요');
	// 	return;
	// }
	const data = {
		name,
		age,
		height: height / 100,
		weight,
		muscle,
		fat,
		sex,
	};
	try {
		const response = await fetch('http://192.168.25.24:8080/inbody', {
			method: 'POST',
			mode: 'cors',
			cache: 'no-cache',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(data),
		});
		const { per_weight, per_muscle, per_fat } = await response.json();
		render(per_weight, per_muscle, per_fat);
	} catch (err) {
		console.log(err);
	}
});

function render(weight, muscle, fat) {


	const weightBarEl = document.querySelector('#weight-bar');
	const muscleBarEl = document.querySelector('#muscle-bar');
	const fatBarEl = document.querySelector('#fat-bar');



	weightBarEl.childNodes[1].style.width = `${weight * 43}%`
	muscleBarEl.childNodes[1].style.width = `${muscle * 43}%`
	fatBarEl.childNodes[1].style.width = `${fat * 43}%`

	weightBarEl.childNodes[3].innerHTML = weight
	muscleBarEl.childNodes[3].innerHTML = muscle
	fatBarEl.childNodes[3].innerHTML = fat


	console.dir(weightBarEl)



}


