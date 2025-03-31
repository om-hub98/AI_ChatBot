import { Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ChatComponent } from './components/chat/chat.component';

export const routes: Routes = [
    {
        path:'',
        redirectTo: 'dashboard',
        pathMatch: 'full'
    },
    {
        path:'dashboard',
        component: DashboardComponent
    },
    {
        path:'chat/:id',
        component: ChatComponent
    }

];
