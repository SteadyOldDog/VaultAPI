package online.kongfei.economy

import net.mamoe.mirai.contact.Member

/**
 * 主要的经济API
 *
 */

interface Economy {
    /**
     * 检查economy方法是否开启了
     * 返回 [Success]或[Failure]
     */
    fun isEnabled(): Boolean

    /**
     * 获取经济方法的名称name
     * 返回经济方法的名称name
     */
    fun getName(): String?

    /**
     * 如果给定的实现支持银行返回true
     */
    fun hasBankSupport(): Boolean

    /**
     * 一些经济插件会对数字进行四舍五入
     * 此功能返回插件保留的小数位数
     * 或者返回-1，如果没有进行四舍五入
     */
    fun fractionalDigits(): Int

    /**
     * 将金额格式化为人类可读的字符串
     * 这提供了经济特定格式的转换，以提高插件之间的一致性
     */
    fun format(amount: Double): String?

    /**
     * 返回复数形式的货币名称
     * 如果正在使用的货币不支持货币名称，则将返回空字符串
     */
    fun currencyNamePlural(): String?

    /**
     * 返回单数形式的货币名称
     * 如果正在使用的货币不支持货币名称，则将返回空字符串
     */
    fun currencyNameSingular(): String?

    @Deprecated("弃用的方法",
        replaceWith = ReplaceWith("hasAccount(member: Member?)"),
        level = DeprecationLevel.WARNING)
    fun hasAccount(memberName: String?): Boolean

    /**
     * 检查成员是否有一个账户
     * 如果玩家至少加入群一次，这将返回 true，因为所有经济插件都会在玩家加入时自动生成玩家账户
     *
     * @param member 被检查的成员
     */
    fun hasAccount(member: Member?): Boolean

    /**
     * 检查该玩家是否在给定群上拥有账户
     * 如果玩家至少加入群一次，这将返回 true，因为所有经济插件都会在玩家加入时自动生成玩家账户
     *
     * @param member 被检查的在指定群的群员
     * @param groupId 指定群的账户
     */
    fun hasAccount(member: Member?, groupId: Long?): Boolean

    /**
     * 获取一名成员的资金
     *
     * @param member 成员
     */
    fun getBalance(member: Member?): Double

    /**
     * 获取该玩家在给定群上的资金
     * 如果一个经济插件不支持这个实现，那么将返回全局的资金
     *
     * @param member 被检查的在指定群的群员
     * @param groupId 指定的群
     */
    fun getBalance(member: Member?, groupId: Long?): Double

    /**
     * 检查成员的账户资金是否有相应数量 - 不要使用负数
     *
     * @param member 被检查的群员
     * @param amount 检查的数量
     * @return 返回True 如果 **某成员** 拥有 **某数量**, 否则返回False
     */
    fun has(member: Member?, amount: Double): Boolean

    /**
     * 检查指定群的成员的账户资金是否有相应数量 - 不要使用负数
     * 如果一个经济插件不支持这个实现，那么将返回全局的资金
     *
     * @param member 被检查的群员
     * @param groupId 指定的群
     * @param amount 检查的数量
     * @return 返回True 如果 **某成员** 拥有 **某数量**, 否则返回False
     */
    fun has(member: Member?, groupId: Long?, amount: Double): Boolean

    /**
     * 从一名成员提取一定数量的资金 - 不要使用负数
     *
     * @param member 要提取的成员
     * @param amount 要提取的数量
     * @return 操作（交易）返回的详细信息
     */
    fun withdrawMember(member: Member?, amount: Double): EconomyResponse?

    /**
     * 从指定群的一名成员提取一定数量的资金 - 不要使用负数
     * 如果一个经济插件不支持这个实现，那么将返回全局的资金
     *
     * @param member 要提取的成员
     * @param groupId 指定的群
     * @param amount 要提取的数量
     * @return 操作（交易）返回的详细信息
     */
    fun withdrawMember(member: Member?, groupId: Long?, amount: Double): EconomyResponse?

    /**
     * 向一名成员存入一定数量的资金 - 不要使用负数
     *
     * @param member 要存入的成员
     * @param amount 要存入的数量
     * @return 操作（交易）返回的详细信息
     */
    fun depositMember(member: Member?, amount: Double): EconomyResponse?

    /**
     * 向指定群的一名成员存入一定数量的资金 - 不要使用负数
     * 如果一个经济插件不支持这个实现，那么将返回全局的资金
     *
     * @param member 要存入的成员
     * @param groupId 指定的群
     * @param amount 要存入的数量
     * @return 操作（交易）返回的详细信息
     */
    fun depositMember(member: Member?, groupId: Long?, amount: Double): EconomyResponse?

    /**
     * 使用指定的名称创建一个成员的银行账户
     *
     * @param name 账户名称
     * @param member 账户链接的成员
     * @return 操作（交易）返回的详细信息
     */
    fun createBank(name: String?, member: Member?): EconomyResponse?

    /**
     * 使用指定名称删除银行账户
     *
     * @param name 需要删除的银行账户名称
     * @return 操作是否完全成功
     */
    fun deleteBank(name: String?): EconomyResponse?

    /**
     * 返回银行拥有的资金的数量
     *
     * @param name 账户名称
     * @return 返回EconomyResponse对象
     */
    fun bankBalance(name: String?): EconomyResponse?

    /**
     * 返回true或false，根据银行是否有指定数量的资金 - 不要使用负数
     *
     * @param name 账户名称
     * @param amount 检查的数量
     * @return 返回EconomyResponse对象
     */
    fun bankHas(name: String?, amount: Double): EconomyResponse?

    /**
     * 从银行里提取一定数量的资金 - 不要使用负数
     *
     * @param name 账户名称
     * @param amount 提取的数量
     * @return 返回EconomyResponse对象
     */
    fun bankWithdraw(name: String?, amount: Double): EconomyResponse?

    /**
     * 将一定数量的资金存入银行 - 不要使用负数
     *
     * @param name 账户名称
     * @param amount 存入的数量
     * @return 返回EconomyResponse对象
     */
    fun bankDeposit(name: String?, amount: Double): EconomyResponse?

    /**
     * 检查一名成员是否是一个银行账户的拥有者
     *
     * @param name 账户名称
     * @param member 要检查的成员
     * @return 返回EconomyResponse对象
     */
    fun isBankOwner(name: String?, member: Member?): EconomyResponse?

    /**
     * 检查一名成员是否是银行账户的成员
     *
     * @param name 账户名称
     * @param member 要检查的成员
     * @return 返回EconomyResponse对象
     */
    fun isBankMember(name: String?, member: Member?): EconomyResponse?

    /**
     * 获取银行的列表
     * @return 返回银行的列表
     */
    fun getBanks(): List<String?>?

    /**
     * 根据给定成员尝试创建一名成员账户
     * @param member 给定成员
     * @return 账户是否创建成功
     */
    fun createPlayerAccount(member: Member?): Boolean

    /**
     * 根据指定群的一个成员尝试创建一名成员账户
     * 如果一个经济插件不支持此操作则永远返回false
     * @param member 指定群的给定成员
     * @param groupId 指定的群
     * @return 账户是否创建成功
     */
    fun createPlayerAccount(member: Member?, groupId: Long?): Boolean

}