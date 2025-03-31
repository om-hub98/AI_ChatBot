import { Component, EventEmitter, inject, Input, Output } from '@angular/core';

import { ChatAPIService } from '../../services/chat-api.service';
import { ChatConversation } from '../../model/interface/message';
import { RouterModule } from '@angular/router';


@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {
  // @Output() conversationSelected = new EventEmitter<string>();
  // chatAPIservice = inject(ChatAPIService);
  // @Input() conversationHistory: any;

  // ngOnInit(): void {}

  // onConversationClick(conversations: any) {
  //   console.log('Conversation clicked:', conversations);
  //   this.conversationSelected.emit(conversations);
  //}
}
