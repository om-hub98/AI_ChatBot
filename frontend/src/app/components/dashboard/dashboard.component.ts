import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject, OnInit, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ChatAPIService } from '../../services/chat-api.service';
import { ChatConversation } from '../../model/interface/message';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';
import { ChatComponent } from '../chat/chat.component';
import { HistoryComponent } from '../history/history.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [FormsModule, CommonModule, SidebarComponent, ToastModule, ChatComponent, HistoryComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  messages: Array<{ role: string; question: string; answer: string;}> = [];
  userInput: string = '';
  selectedModel: string = '';    // default model
  viewHistory: string = '';
  themeToggle: string = 'dark';
  http = inject(HttpClient);          // it injects dependency/objects 
  chatAPIservice = inject(ChatAPIService);
  toastMessageService = inject(MessageService);
  conversationId: string = '';
  allConversationHistory: Array<{ conversationId: number; title: string, conversations: Array<any> }> = [];
  question : string = '';
  answer : string = '';
  sidebarOpen = false;
  isSidebarVisible = true;

  //constructor(private http : HttpClient) {}      // constructor - old way of dependency injection  

  ngOnInit(): void {
    this.handleAllConversation();
  }

  toggleSidebar(): void {
    this.sidebarOpen = !this.sidebarOpen;
    this.isSidebarVisible = !this.isSidebarVisible;
  }

  // handle new chat
  newChat(): void {
    this.messages = [];
    this.selectedModel = '';
    this.userInput = '';
    this.conversationId = '';
  }

  // Handle model change  and also Reset messages for the new model
  onModelChange(): void {
    if (this.selectedModel) {
      this.messages = [];     
    }
  }

  //  adjust height of the propmt textarea
  adjustHeight(event: Event): void {
    const textarea = event.target as HTMLTextAreaElement;
    textarea.style.height = 'auto';                          
    textarea.style.height = `${textarea.scrollHeight}px`;    
  }

  // Send message to server
  sendMessage(event?: Event): void {
    if (event) {
      event.preventDefault(); 
    }
    if (!this.selectedModel) {
      alert("Model not selected");
      this.showError();
      return;
    }
    if (this.userInput.trim()) {
      const body = {
        'conversationId': this.conversationId === '' ? '' : this.conversationId,
        'aiName': this.selectedModel,
        'message': this.userInput
      }
      this.chatAPIservice.sendMessageToServer(body).subscribe((response: any) => {
        this.messages.push({ role: response.aiName, question: response.inputMessage, answer: response.outputMessage });
        this.conversationId = response.conversationId;
        console.log('Response:', response);
        this.handleAllConversation();  
      },
        error => {
          console.error('Error:', error);
        });
      this.userInput = '';
      const textarea = document.querySelector('textarea');
      if (textarea) {
        textarea.style.height = '4';
        textarea.style.overflow = 'hidden';
      }
    }
  }

  // handle chat selection from hisory 
  handleConversationSelectionFromHistory(conversationId : any): void {
    this.messages = [];
    this.chatAPIservice.getConversationById(conversationId).subscribe((response: ChatConversation) =>{
      const conversation = response.messages;
      for(let i=0; i<conversation.length;){
        this.question = conversation[i].message;  
        if(conversation[i+1].role == 'User'){
          this.answer = "";
          i++;
        }
        else{
          this.answer = conversation[i+1].message;
          i+=2;
        }
        this.messages.push({ role: 'User', question: this.question, answer: this.answer });
      }
    });
    console.log('Messages:', this.messages);
  }

  // Show error toast
  showError(): void {
    this.toastMessageService.add({
      severity: 'error',
      summary: 'Model Not Selected',
      detail: 'Please select a model to start the conversation.'
    });
  }

  // Add conversation to history
  handleAllConversation(): void {
    this.chatAPIservice.getAllConversation().subscribe((response: ChatConversation[]) => {
      this.allConversationHistory = [];
      response.reverse().forEach(conversation => {
        this.allConversationHistory.push({ conversationId: conversation.conversationId, title: this.formatTitle(conversation.messages[0].message), conversations: conversation.messages });
      });
    });
  }

  formatTitle(title: string): string {
    return title.length > 20 ? title.substring(0, 20)+ "..." : title;
  }
}
