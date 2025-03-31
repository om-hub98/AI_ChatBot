import { Component, EventEmitter, inject, Input, Output } from '@angular/core';
import { ChatAPIService } from '../../services/chat-api.service';

@Component({
  selector: 'app-history',
  standalone: true,
  imports: [],
  templateUrl: './history.component.html',
  styleUrl: './history.component.css'
})
export class HistoryComponent {
    @Output() conversationSelected = new EventEmitter<string>();
    @Input() conversationHistory: any;
    @Input() isOpen = true;
    @Output() closeHistorySidebar = new EventEmitter<void>();
  
    ngOnInit(): void {}
  
    // onConversationClick(conversations: any) {
    //   console.log('Conversation clicked:', conversations);
    //   this.conversationSelected.emit(conversations);
    // }
    onConversationClick(conversationId: any) {
      this.conversationSelected.emit(conversationId);
    }
    
    closeSideBar():void {
      this.closeHistorySidebar.emit();
    }

}
