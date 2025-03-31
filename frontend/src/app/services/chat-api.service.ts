import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ChatConversation } from '../model/interface/message';
import { environment } from '../../enviroments/enviroments.development';

@Injectable({
  providedIn: 'root'
})
export class ChatAPIService {

  constructor(private http : HttpClient) { }


  getAllConversation(): Observable<ChatConversation[]>{
    return this.http.get<ChatConversation[]>(environment.API_Url+'/conversations');
  }
  
  getConversationById(id: string): Observable<ChatConversation>{
    return this.http.get<ChatConversation>(`${environment.API_Url}/conversations/id?conversationId=${id}`);
  }

  sendMessageToServer(body: any): Observable<any> { 
    const url = environment.API_Url+'/chat';
    return this.http.post(url, body);
  }
}
