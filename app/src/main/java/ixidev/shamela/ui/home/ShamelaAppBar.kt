package ixidev.shamela.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import ixidev.shamela.R

/**
 * Created by Abdelmajid ID ALI, on 30/05/2021
 * Github : [https://github.com/ixiDev]
 */
@Composable
fun ShamelaTopAppBar() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (title, settings) = createRefs()
        Text(
            modifier = Modifier
                .padding(end = 5.dp)
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                },
            text = stringResource(id = R.string.app_name),
            color = MaterialTheme.colors.secondary,
            fontSize = 25.sp,
        )

        Box(
            Modifier
                .constrainAs(settings) {
                    width = Dimension.fillToConstraints
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(title.start)
                }
                .height(50.dp)
        ) {

            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                modifier = Modifier
                    .size(30.dp)
                    .padding(start = 5.dp)
                    .clickable(enabled = true, role = Role.Button) {
                        //
                    }
                    .align(Alignment.CenterStart),
                tint = MaterialTheme.colors.secondary
            )
        }
    }

}