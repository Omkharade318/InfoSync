package com.example.infosync.news.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.infosync.news.domain.model.Source

@ProvidedTypeConverter
class NewsTypeConvertor {  // The Room database takes only primitive types as input and thus we need to convert the source into String

    @TypeConverter
    fun sourceToString(source: Source): String{
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(source: String): Source {
        return source.split(",").let { sourceArray->
            Source(sourceArray[0],sourceArray[1] )
        }
    }



}