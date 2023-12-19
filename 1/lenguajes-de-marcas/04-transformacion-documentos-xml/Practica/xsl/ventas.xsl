<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/clientes">
        <html>
            <head>
                <link rel="stylesheet" href="../styles/style.css" />
                <title>Ventas</title>
            </head>
            <body>
                <h2>Ventas</h2>
                <table>
                    <tr>
                        <th>Id</th>
                        <th>Ciudad</th>
                        <th>Facturacion</th>
                        <th>Pedidos</th>
                    </tr>
                    <xsl:for-each select="cliente">
                        <tr>
                            <td>
                                <xsl:value-of select="@id" />
                            </td>
                            <td>
                                <xsl:value-of select="direccion/ciudad" />
                            </td>
                            <td>
                                <xsl:value-of select="facturacion" />
                            </td>
                            <td>
                                <xsl:value-of select="pedidos" />
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>