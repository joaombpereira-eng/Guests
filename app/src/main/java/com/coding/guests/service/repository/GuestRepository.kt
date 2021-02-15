package com.coding.guests.service.repository

import android.content.Context
import com.coding.guests.service.model.GuestModel

class GuestRepository (context: Context) {

    // Acesso ao banco de dados
    private val mDataBase = GuestDataBase.getDatabase(context).guestDAO()

    /**
     * Carrega convidado
     */
    fun get(id: Int): GuestModel = mDataBase.get(id)

    /**
     * Insere convidado
     */
    fun save(guest: GuestModel): Boolean = mDataBase.save(guest) > 0

    /**
     * Faz a listagem de todos os convidados
     */
    fun getAll(): List<GuestModel> = mDataBase.getInvited()

    /**
     * Faz a listagem de todos os convidados presentes
     */
    fun getPresent(): List<GuestModel> = mDataBase.getPresent()

    /**
     * Faz a listagem de todos os convidados ausentes
     */
    fun getAbsent(): List<GuestModel> = mDataBase.getAbsent()

    /**
     * Atualiza convidado
     */
    fun update(guest: GuestModel): Boolean = mDataBase.update(guest) > 0

    /**
     * Remove convidado
     */
    fun delete(guest: GuestModel) = mDataBase.delete(guest)
}