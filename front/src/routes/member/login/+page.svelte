<script lang="ts">
	import type { components, paths } from '$lib/types/api/v1/schema';
	import createClient from 'openapi-fetch';

	const client = createClient<paths>({
		baseUrl: import.meta.env.VITE_CORE_API_BASE_URL,
		credentials: 'include'
	});

	async function submitForm(this: HTMLFormElement) {
		const form = this;

		const { data, error } = await client.POST('/api/v1/members/login', {
			body: {
				username: form.username.value,
				password: form.password.value
			}
		});
	}

	const kakaoLoginUrl = `${
		import.meta.env.VITE_CORE_API_BASE_URL
	}/member/socialLogin/kakao?redirectUrl=${encodeURIComponent(
		import.meta.env.VITE_CORE_FRONT_BASE_URL
	)}/`;
</script>

<form on:submit|preventDefault={submitForm}>
	<input type="text" placeholder="Username" name="username" />
	<input type="password" placeholder="Password" name="password" />
	<button type="submit">Login</button>
	<a href={kakaoLoginUrl}>Kakao</a>
</form>
