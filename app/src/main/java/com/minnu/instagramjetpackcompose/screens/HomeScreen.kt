package com.minnu.instagramjetpackcompose.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.minnu.instagramjetpackcompose.R
import com.minnu.instagramjetpackcompose.model.Post
import com.minnu.instagramjetpackcompose.model.Stories
import com.minnu.instagramjetpackcompose.model.User

@Composable
fun HomeScreen() {
    InstagramPreview()
}

@Preview(showBackground = true)
@Composable
fun InstagramPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        AppToolbar()
        StoriesSection(stories = getStories())
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .height(1.dp)
        )
        PostSection(posts = getPostData())
    }
}

@Composable
fun AppToolbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Image(
                painter = painterResource(id = R.drawable.insta_logo), contentDescription = ""
            )
        }
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            imageIcon(id = R.drawable.add, size = 20)
            Spacer(modifier = Modifier.width(10.dp))
            imageIcon(id = R.drawable.heart, size = 20)
            Spacer(modifier = Modifier.width(10.dp))
            imageIcon(id = R.drawable.messenger, size = 20)
        }
    }
}

@Composable
fun imageIcon(id: Int, size: Int) {
    Image(
        painter = painterResource(id = id),
        contentDescription = "",
        modifier = Modifier.size(size.dp)
    )
}

@Composable
fun StoriesSection(stories: List<Stories>) {
    LazyRow {
        items(stories) { item ->
            StoryItem(item)
        }
    }
}

@Composable
fun StoryItem(user: Stories) {
    Column(modifier = Modifier.padding(5.dp, 10.dp, 5.dp, 10.dp)) {
        Image(
            painter = painterResource(id = user.profile),
            contentDescription = "",
            modifier = Modifier
                .size(60.dp)
                .border(
                    width = 2.dp, brush = Brush.linearGradient(
                        listOf(
                            Color("#DE0046".toColorInt()),
                            Color("#F7A34B".toColorInt()),
                        )
                    ), shape = CircleShape
                )
                .padding(5.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = user.userName,
            fontSize = 12.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(60.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
    }
}

@Composable
fun PostSection(posts: List<Post>) {
    LazyColumn {
        items(posts) { post ->
            PostItem(post)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostItem(post: Post) {

    val pageState = rememberPagerState()

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = post.profile),
                    contentDescription = "",
                    modifier = Modifier
                        .size(30.dp)
                        .border(
                            width = 1.dp, brush = Brush.linearGradient(
                                listOf(
                                    Color("#DE0046".toColorInt()),
                                    Color("#F7A34B".toColorInt()),
                                )
                            ), shape = CircleShape
                        )
                        .padding(1.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = post.userName,
                    fontSize = 12.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
            }


            Image(
                painter = painterResource(id = R.drawable.ic_more),
                contentDescription = "",
                modifier = Modifier
                    .size(22.dp)
                    .align(Alignment.CenterEnd)
            )
        }

        Spacer(modifier = Modifier.height(2.dp))

        PostCarousel(post.postImageList, pageState)

        Spacer(modifier = Modifier.height(8.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                imageIcon(id = R.drawable.heart, size = 20)
                Spacer(modifier = Modifier.width(16.dp))
                imageIcon(id = R.drawable.messenger, size = 20)
                Spacer(modifier = Modifier.width(16.dp))
                imageIcon(id = R.drawable.share, size = 20)
            }


            HorizontalPagerIndicator(
                pageCount = post.postImageList.size,
                pagerState = pageState,
                activeColor = Color("#32B5FF".toColorInt()),
                inactiveColor = Color("#C4C4C4".toColorInt()),
                modifier = Modifier.align(Alignment.Center),
            )

            Image(
                painter = painterResource(id = R.drawable.bookmark),
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.CenterEnd)
                    .padding(end = 10.dp)
            )
        }

        Spacer(modifier = Modifier.height(1.dp))

        LikedBySection(post.likedBy)

        Description(post)

        Spacer(modifier = Modifier.height(7.dp))
    }
}

@Composable
fun Description(post: Post) {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(Color.Black, fontWeight = FontWeight.Bold)) {
            append("${post.userName} ")
        }
        append(post.description)
    }

    Text(
        text = annotatedString,
        fontSize = 11.sp,
        color = Color.Black,
        overflow = TextOverflow.Ellipsis,
        maxLines = 2,
        modifier = Modifier.padding(horizontal = 10.dp)
    )
}

@Composable
fun LikedBySection(likedBy: List<User>) {

    if (likedBy.size > 10) {
        Text(
            text = "${likedBy.size} likes",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    } else if (likedBy.size == 1) {
        Text(
            text = "Liked by ${likedBy[0].userName}",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 5.dp)
        )

    } else {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(3.dp)
        ) {
            PostLikeViewByProfile(likedBy)
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = "Liked by ${likedBy[0].userName} and liked by ${likedBy.size - 1} others",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = Color.Black
            )
        }
    }
}

@Composable
fun PostLikeViewByProfile(likedBy: List<User>) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy((-5).dp)) {
        items(likedBy.take(4)) { likesBy ->
            Image(
                painter = painterResource(id = likesBy.profile),
                contentDescription = "story profile",
                modifier = Modifier
                    .size(24.dp)
                    .border(
                        width = 2.dp, color = Color.White, shape = CircleShape
                    )
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostCarousel(postImageList: List<Int>, pagerState: PagerState) {
    Box(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(
            pageCount = postImageList.size, state = pagerState, modifier = Modifier.fillMaxWidth()
        ) { currentPage ->
            Image(
                painter = painterResource(id = postImageList[currentPage]),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(375.dp),
                contentScale = ContentScale.Crop
            )
        }

        NudgeCount(modifier = Modifier.align(Alignment.TopEnd), pagerState)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NudgeCount(modifier: Modifier, pagerState: PagerState) {

    Row(
        modifier = modifier
            .padding(top = 6.dp, end = 6.dp)
            .clip(CircleShape)
            .background(Color.Black.copy(0.4f))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(text = "${pagerState.currentPage}", color = Color.White, fontSize = 11.sp)
        Text(text = "/", color = Color.White, fontSize = 10.sp)
        Text(text = "${pagerState.currentPage}", color = Color.White, fontSize = 11.sp)
    }
}

private fun getStories(): List<Stories> {
    val users = listOf(
        Stories("John", R.drawable.avatar1),
        Stories("Michael Jackson", R.drawable.avatar2),
        Stories("Rihana", R.drawable.avatar3),
        Stories("Mickey Aurthor", R.drawable.avatar4),
        Stories("Kumail", R.drawable.avatar1),
        Stories("Michael Jackson", R.drawable.avatar2),
        Stories("Rihana", R.drawable.avatar3),
        Stories("Mickey Aurthor", R.drawable.avatar4),
    )
    return users
}

private fun getPostData(): List<Post> = listOf(
    Post(
        userName = "Aleena",
        profile = R.drawable.avatar1,
        postImageList = listOf(R.drawable.serge),
        description = "lets down the haters",
        likedBy = listOf(
            User(profile = R.drawable.avatar1, userName = "John"),
            User(profile = R.drawable.avatar2, userName = "Michael Jackson"),
            User(profile = R.drawable.avatar3, userName = "Rihana"),
            User(profile = R.drawable.avatar4, userName = "Mickey Aurthor"),
            User(profile = R.drawable.avatar1, userName = "Kumail"),
            User(profile = R.drawable.avatar2, userName = "John"),
        )
    ), Post(
        userName = "Sergi Constance",
        profile = R.drawable.serge_avatar,
        postImageList = listOf(R.drawable.sergi_1, R.drawable.sergi_2, R.drawable.sergi_3),
        description = "lets down the haters, lets down the haters",
        likedBy = listOf(
            User(profile = R.drawable.avatar4, userName = "John"),
            User(profile = R.drawable.avatar3, userName = "Michael Jackson"),
            User(profile = R.drawable.avatar2, userName = "Rihana"),
            User(profile = R.drawable.avatar1, userName = "Mickey Aurthor"),
            User(profile = R.drawable.avatar3, userName = "Kumail"),
            User(profile = R.drawable.avatar2, userName = "John"),
            User(profile = R.drawable.avatar4, userName = "John"),
            User(profile = R.drawable.avatar3, userName = "Michael Jackson"),
            User(profile = R.drawable.avatar2, userName = "Rihana"),
            User(profile = R.drawable.avatar1, userName = "Mickey Aurthor"),
            User(profile = R.drawable.avatar3, userName = "Kumail"),
            User(profile = R.drawable.avatar2, userName = "John"),
        )
    ), Post(
        userName = "Tariq Jameel Official",
        profile = R.drawable.avatar2,
        postImageList = listOf(R.drawable.car, R.drawable.pic1),
        description = "lets down the haters, lets down the haters",
        likedBy = listOf(
            User(profile = R.drawable.avatar4, userName = "John"),
            User(profile = R.drawable.avatar3, userName = "Michael Jackson"),
            User(profile = R.drawable.avatar2, userName = "Rihana"),
            User(profile = R.drawable.avatar1, userName = "Mickey Aurthor"),
            User(profile = R.drawable.avatar3, userName = "Kumail"),
            User(profile = R.drawable.avatar2, userName = "John"),
            User(profile = R.drawable.avatar4, userName = "John"),
            User(profile = R.drawable.avatar3, userName = "Michael Jackson"),
            User(profile = R.drawable.avatar2, userName = "Rihana"),
            User(profile = R.drawable.avatar1, userName = "Mickey Aurthor"),
            User(profile = R.drawable.avatar3, userName = "Kumail"),
            User(profile = R.drawable.avatar2, userName = "John"),
        )
    )

)
