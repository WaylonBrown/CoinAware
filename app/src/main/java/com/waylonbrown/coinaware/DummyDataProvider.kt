package com.waylonbrown.coinaware

import com.github.mikephil.charting.data.Entry

class DummyDataProvider {
    
    data class DummyHeaderListData(val data: List<Entry>)
    
    fun getDummyHeaderData(): DummyHeaderListData {
        return DummyHeaderListData(getDummyChartData())
    }

    private fun getDummyChartData(): List<Entry> {
        val list = mutableListOf<Entry>()
        list.add(Entry(0F, 10F))
        list.add(Entry(1F, 8F))
        list.add(Entry(2F, 7F))
        list.add(Entry(3F, 8F))
        list.add(Entry(4F, 7F))
        list.add(Entry(5F, 7F))
        list.add(Entry(6F, 10F))
        list.add(Entry(7F, 12F))
        list.add(Entry(8F, 13F))
        list.add(Entry(9F, 17F))
        list.add(Entry(10F, 16F))
        list.add(Entry(11F, 15F))
        list.add(Entry(12F, 12F))
        list.add(Entry(13F, 10F))
        list.add(Entry(14F, 11F))
        list.add(Entry(15F, 11F))
        list.add(Entry(16F, 12F))
        list.add(Entry(17F, 13F))
        list.add(Entry(18F, 12F))
        list.add(Entry(19F, 10F))
        list.add(Entry(20F, 8F))
        return list
    }
}