<script lang="ts">
	import type { components, paths } from '$lib/types/api/v1/schema';
	import createClient from 'openapi-fetch';

	import { onMount } from 'svelte';

	const client = createClient<paths>({
		baseUrl: import.meta.env.VITE_CORE_API_BASE_URL,
		credentials: 'include'
	});

	let post = $state<components['schemas']['PostDto']>();

	onMount(async () => {
		const { data, error } = await client.GET('/api/v1/posts/{id}', {
			params: {
				path: {
					id: 1
				}
			}
		});

		if (data) post = data.data.item;
	});
</script>

{#if post}
	<h1>
		{post.id}
	</h1>

	<div>
		{post.title}
	</div>
{/if}
