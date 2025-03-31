import { Component,Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [],
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent implements OnInit {
  @Input() chats: Array<{ role: string; question: string; answer: string }> = [];

  ngOnInit(): void {
  }
}
