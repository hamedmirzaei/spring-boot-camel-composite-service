<customer>
  <id>{data(/all/customer/@id)}</id>
  <orders>
    { for $baseinfos
      in  /all/baseinfos/order
      return <order><id>{data($order/@id)}</id><product name='{data($order/@product)}'/></order>
    }
  </orders>
</customer>