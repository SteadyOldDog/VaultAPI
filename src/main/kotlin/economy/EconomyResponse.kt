package online.kongfei.economy

class EconomyResponse(amount: Double, balance: Double, type: ResponseType?, errorMessage: String?) {
    /**
     * 方法调用状态响应的枚举
     */
    enum class ResponseType(val id: Int) {
        SUCCESS(1),
        FAILURE(2),
        NOT_IMPLEMENTED(3)

    }

    /**
     * Amount modified by calling method
     * 调用的方法修改后的金额
     */
    val amount = 0.0

    /**
     * New balance of account
     * 新的账户的金额
     */
    val balance = 0.0

    /**
     * 调用成功或失败。使用ResponseType枚举确保结果有效
     */
    val type: ResponseType? = null

    /**
     * 如果type是ResponseType.FAILURE时的错误信息
     */
    val errorMessage: String? = null

    /**
     * 检查操作（交易）是否成功
     */
    fun transactionSuccess(): Boolean {
        return type == ResponseType.SUCCESS
    }

}