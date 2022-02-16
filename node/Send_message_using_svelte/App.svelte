<script>
	const formValues = {
		telephone : '',
		message : '',
		country : ''
	}

	function sendMessage(event){
		event.preventDefault()
		console.log(formValues)

		var myHeaders = new Headers();
			myHeaders.append("Content-Type", "application/json");
			myHeaders.append("Authorization", "Bearer YOUR_KEY");

			var raw = JSON.stringify({
			"from": "YOUR_TELNYX_NUMBER",
			"to": formValues.country+formValues.telephone,
			"text": formValues.message
			});

			var requestOptions = {
			method: 'POST',
			headers: myHeaders,
			body: raw,
			redirect: 'follow'
			};

			fetch("https://api.telnyx.com/v2/messages", requestOptions)
			.then(response => response.text())
			.then(result => console.log(result))
			.catch(error => console.log('error', error));
	}
</script>

<main>
	<form on:submit={sendMessage}>
		<div>
			<label for="country"><h1>Country</h1></label>
			<select id="country" bind:value={formValues.country}>
				<option value="">Select a Country</option>
				<option value="+91">India</option>
				<option value="+1">USA</option>
				<option value="+61">Australia</option>
			</select>
		</div>
		<div>
			<label for="telephone"><h1>Telephone Number</h1></label>
			<input type="number" id="telephone" bind:value={formValues.telephone}/>
		</div>
		<div>
			<label for="message"><h1>Message</h1></label>
			<textarea id="message" bind:value={formValues.message}/>
		</div>

		<div>
			<button>Send</button>
		</div>
	</form>

</main>

<style>
	
	main {
		/* text-align: center; */
		padding: 1em;
		max-width: 240px;
		margin: 0 auto;
	}

	h1 {
		color: #ff3e00;
		text-transform: uppercase;
		font-size: 1.2em;
		font-weight: 100;
	}

	@media (min-width: 640px) {
		main {
			max-width: none;
		}
	}
</style>