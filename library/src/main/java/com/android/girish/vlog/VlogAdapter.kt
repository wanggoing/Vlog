package com.android.girish.vlog

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.girish.vlog.VlogAdapter.VlogViewHolder

internal class VlogAdapter : RecyclerView.Adapter<VlogViewHolder>() {
    private var mFilteredLogList: List<VlogModel>?
    private var mExpandedModel: VlogModel? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VlogViewHolder {
        return VlogViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_log,
                parent,
                false
            )
        )
    }

    fun extractNumber(log: String): Int? {
        // 使用正则表达式来匹配括号内的数字
        val regex = "\\((\\d+)\\)".toRegex()
    
        // 查找匹配的结果
        val matchResult = regex.find(log)
    
        // 提取匹配结果中的数字并将其转换为 Int
        return matchResult?.groups?.get(1)?.value?.toInt()
    }

    fun intToColor(colorInt: Int): Color {
        // 提取 RGB 分量
        val red = (colorInt shr 16) and 0xFF
        val green = (colorInt shr 8) and 0xFF
        val blue = colorInt and 0xFF

        // 创建并返回颜色对象
        return Color(red, green, blue)
    }


    override fun onBindViewHolder(holder: VlogViewHolder, position: Int) {
        val model = mFilteredLogList!![position]
        val priority = model.logPriority
        val errorColor = Color.parseColor("#990000") // red
        val warnColor = Color.parseColor("#000099") // blue
        val defaultColor = Color.BLACK // black
        defaultColor = intToColor(extractNumber(model.logPriority))

        when (priority) {
            VlogModel.ERROR -> {
                holder.logTag.setTextColor(errorColor)
                holder.logMessage.setTextColor(errorColor)
            }
            VlogModel.WARN -> {
                holder.logTag.setTextColor(warnColor)
                holder.logMessage.setTextColor(warnColor)
            }
            else -> {
                holder.logTag.setTextColor(defaultColor)
                holder.logMessage.setTextColor(defaultColor)
            }
        }
        holder.logTag.text = getLogPriorityInitials(model.logPriority) + "/" + model.tag + ": "
        val isExpanded = model == mExpandedModel
        holder.logMessage.text =
            if (isExpanded)
                model.logMessage
            else if (model.logMessage.length > 50)
                model.logMessage.substring(0, 49) + "..."
            else
                model.logMessage

        holder.expandCollapseArrow.setImageResource(if (isExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down)
        holder.itemView.setOnClickListener {
            mExpandedModel = if (isExpanded) null else model
            // TODO: @girish optimize if required -> update the item rather than the whole list.
            notifyDataSetChanged()
        }
    }

    private fun getLogPriorityInitials(logPriority: Int): String {
        return when (logPriority) {
            VlogModel.DEBUG -> "D"
            VlogModel.ERROR -> "E"
            VlogModel.INFO -> "I"
            VlogModel.VERBOSE -> "V"
            VlogModel.WARN -> "W"
            else -> ""
        }
    }

    override fun getItemCount(): Int {
        return if (mFilteredLogList != null) mFilteredLogList!!.size else 0
    }

    inner class VlogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var logTag: TextView
        var logMessage: TextView
        var expandCollapseArrow: ImageView

        init {
            logTag = itemView.findViewById(R.id.log_tag)
            logMessage = itemView.findViewById(R.id.log_message)
            expandCollapseArrow = itemView.findViewById(R.id.arrow_img)
        }
    }

    fun addLogs(logs: List<VlogModel>?) {
        mFilteredLogList = logs
        notifyDataSetChanged()
    }

    companion object {
        private val TAG = VlogAdapter::class.java.simpleName
    }

    init {
        mFilteredLogList = ArrayList()
    }
}
