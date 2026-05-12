package com.example.applicationex.lab10.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestoreSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.tasks.await

class FirebaseItemsRepository : ItemsRepository {
    
    private val firestore = FirebaseFirestore.getInstance().apply {
        firestoreSettings = firestoreSettings {
            isPersistenceEnabled = true
        }
    }
    
    private val itemsCollection = firestore.collection("items")
    
    override fun getAllItemsStream(): Flow<List<Item>> {
        val resultFlow = MutableStateFlow<List<Item>>(emptyList())
        
        itemsCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                return@addSnapshotListener
            }
            
            val items = snapshot?.documents?.mapNotNull { document ->
                document.toObject(Item::class.java)?.copy(id = document.id)
            } ?: emptyList()
            
            resultFlow.value = items
        }
        
        return resultFlow
    }
    
    override fun getItemStream(id: String): Flow<Item?> {
        val resultFlow = MutableStateFlow<Item?>(null)
        
        itemsCollection.document(id).addSnapshotListener { snapshot, error ->
            if (error != null) {
                return@addSnapshotListener
            }
            
            val item = snapshot?.toObject(Item::class.java)?.copy(id = snapshot.id)
            resultFlow.value = item
        }
        
        return resultFlow
    }
    
    override suspend fun insertItem(item: Item) {
        val newItem = item.copy(id = itemsCollection.document().id)
        itemsCollection.document(newItem.id).set(newItem).await()
    }
    
    override suspend fun deleteItem(item: Item) {
        itemsCollection.document(item.id).delete().await()
    }
    
    override suspend fun updateItem(item: Item) {
        itemsCollection.document(item.id).set(item).await()
    }
}
