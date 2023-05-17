<template>
    <div>
        <router-link :to = "`detail/${video.video_id}`" style="color: black;">
            <b-card>
                <template #header>
                    <h4 class="mb-0">헤더?</h4>
                </template>

                <b-card-body>
                    <b-card-title>{{ video.video_title }}</b-card-title>
                    <div>
                        <b-embed type="iframe" aspect="16by9" :src="url" allowfullscreen></b-embed>
                    </div>
                </b-card-body>

                <b-list-group flush>
                    <b-list-group-item>조회수 : {{ video.video_viewCnt }}</b-list-group-item>
                    <b-list-group-item>채널명 : {{ video.video_channelName }}</b-list-group-item>
                </b-list-group>



                <b-card-footer>This is a footer</b-card-footer>

            </b-card>
        </router-link>
    </div>
</template>

<script>


export default {
    name: "VideoCard",

    props: {
        video: Object
    }
    ,
    data() {
        return {
            url: `https://youtube.com/embed/${this.video.video_id}`
        }
    }


};
</script>
  
  