package com.atsy.composelisttrial

import android.os.Bundle
import android.widget.Scroller
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.atsy.composelisttrial.ui.theme.ComposeListTrialTheme
import com.atsy.composelisttrial.viewmodel.MainActivityViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainActivityViewModel by viewModels()

        setContent {
            ComposeListTrialTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    TodoList(viewModel)
                }
            }
        }
    }
}

@Composable
fun TodoList(vm: MainActivityViewModel) {

    Column {

        val coroutine = rememberCoroutineScope()
        val scrollState = rememberLazyListState()

        // 一覧に行を追加するボタン
        Button(onClick = {
            vm.todoItems.add(0, LocalDateTime.now().toString())
            coroutine.launch {
                scrollState.animateScrollToItem(0)
            }
        }){
            Text(text = "行を追加する")
        }

        // 一覧
        LazyColumn(
            reverseLayout = true,
            state = scrollState,
        ){
            items(vm.todoItems) { todo ->
                Text(text = todo)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeListTrialTheme {

        Surface() {
            val vm = MainActivityViewModel()
            vm.todoItems.addAll(listOf("a", "b"))
            TodoList( vm )

        }
    }
}